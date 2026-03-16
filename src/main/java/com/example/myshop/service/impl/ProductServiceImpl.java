package com.example.myshop.service.impl;

import com.example.myshop.constant.CloudinaryConstant;
import com.example.myshop.constant.ImageStatus;
import com.example.myshop.dto.ProductDTO;
import com.example.myshop.entity.Attribute;
import com.example.myshop.entity.Category;
import com.example.myshop.entity.Image;
import com.example.myshop.entity.Product;
import com.example.myshop.entity.ProductImage;
import com.example.myshop.entity.ProductType;
import com.example.myshop.exception.I18nException;
import com.example.myshop.mapper.ProductMapper;
import com.example.myshop.payload.ProductPayload;
import com.example.myshop.payload.ProductTypePayload;
import com.example.myshop.repository.AttributeRepository;
import com.example.myshop.repository.CategoryRepository;
import com.example.myshop.repository.ImageRepository;
import com.example.myshop.repository.ProductImageRepository;
import com.example.myshop.repository.ProductRepository;
import com.example.myshop.repository.predicate.AttributePredicate;
import com.example.myshop.service.CloudinaryService;
import com.example.myshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO create(ProductPayload productPayload) throws I18nException, IOException {
        Category category = categoryRepository.findById(productPayload.getCategoryId())
                .orElseThrow(() -> {
                    return I18nException.builder()
                            .code(HttpStatus.NOT_FOUND)
                            .message("")
                            .build();
                });

        AttributePredicate attributePredicate = new AttributePredicate().inIds(productPayload.getAttributeIds());
        List<Attribute> attribute = attributeRepository.findAll(attributePredicate.getCriteria());
        if (attribute.size() < productPayload.getAttributeIds().size()) {
            throw I18nException.builder()
                    .code(HttpStatus.NOT_FOUND)
                    .message("")
                    .build();
        }

        Product product = Product.builder()
                .name(productPayload.getName())
                .description(productPayload.getDescription())
                .build();
        List<Image> images = uploadImages(productPayload.getImages());
        List<ProductImage> productImages = images.stream().map(image -> {
            return productImageRepository.save(
                    ProductImage.builder()
                            .image(image)
                            .product(product)
                            .build()
            );
        }).toList();
        product.setProductImages(productImages);

        List<ProductType> productTypes = new ArrayList<>();
        for (ProductTypePayload productTypePayload : productPayload.getProductTypes()) {
            ProductType productType = ProductType.builder()
                    .price(productTypePayload.getPrice())
                    .quantity(productTypePayload.getQuantity())
                    .build();
            if (StringUtils.hasText(productTypePayload.getName())) productType.setName(productTypePayload.getName());
            productType.setImage(uploadImage(productTypePayload.getImage()));
            productTypes.add(productType);
        }
        product.setProductTypes(productTypes);
        productRepository.save(product);

        return productMapper.toDto(product);
    }

    private List<Image> uploadImages(List<MultipartFile> images) {
        return images.parallelStream().map(image -> {
            Map<String, Object> result = null;
            try {
                result = cloudinaryService.upload(image, Collections.emptyMap());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return imageRepository.save(Image.builder()
                    .publicId((String) result.get(CloudinaryConstant.PUBLIC_ID))
                    .secureUrl((String) result.get(CloudinaryConstant.SECURE_URL))
                    .resourceType((String) result.get(CloudinaryConstant.RESOURCE_TYPE))
                    .format((String) result.get(CloudinaryConstant.FORMAT))
                    .status(ImageStatus.COMPLETED)
                    .build());
        }).toList();
    }

    private Image uploadImage(MultipartFile image) throws IOException {
        Map<String, Object> result = cloudinaryService.upload(image, Collections.emptyMap());
        return imageRepository.save(Image.builder()
                .publicId((String) result.get(CloudinaryConstant.PUBLIC_ID))
                .secureUrl((String) result.get(CloudinaryConstant.SECURE_URL))
                .resourceType((String) result.get(CloudinaryConstant.RESOURCE_TYPE))
                .format((String) result.get(CloudinaryConstant.FORMAT))
                .status(ImageStatus.COMPLETED)
                .build());
    }
}
