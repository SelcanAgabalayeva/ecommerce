package az.edu.itbrains.ecommerce.services.Impls;

import az.edu.itbrains.ecommerce.dtos.product.ProductCreateDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductHomeDto;
import az.edu.itbrains.ecommerce.dtos.product.ProductUpdateDto;
import az.edu.itbrains.ecommerce.exceptions.ResourceNotFoundException;
import az.edu.itbrains.ecommerce.models.Category;
import az.edu.itbrains.ecommerce.models.Product;
import az.edu.itbrains.ecommerce.payloads.ApiResponse;
import az.edu.itbrains.ecommerce.repositories.CategoryRepository;
import az.edu.itbrains.ecommerce.repositories.ProductRepository;
import az.edu.itbrains.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductHomeDto> getHomeProducts() {
        List<Product> findProduct=productRepository.findAll();
        List<ProductHomeDto> products =findProduct.stream().map(product -> modelMapper.map(product,ProductHomeDto.class)).collect(Collectors.toList());
        return products;
    }

    @Override
    public ApiResponse create(ProductCreateDto productCreateDto) {
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(productCreateDto.getCategoryId());
            if (categoryOptional.isEmpty()) {
                return new ApiResponse("Category not found", false, HttpStatus.NOT_FOUND);
            }

            Product product = new Product();
            product.setName(productCreateDto.getName());
            product.setPrice(productCreateDto.getPrice());
            product.setDiscountPrice(productCreateDto.getDiscountPrice());
            product.setShortDescription(productCreateDto.getShortDescription());
            product.setDescription(productCreateDto.getDescription());
            product.setInformation(productCreateDto.getInformation());
            product.setCategory(categoryOptional.get());
            productRepository.save(product);
            return new ApiResponse("Product created successfully", true, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ApiResponse(e.getMessage(),false, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ProductUpdateDto getUpdate(Long id) {
        Product findProduct=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product","id",id));
        ProductUpdateDto productUpdateDto =modelMapper.map(findProduct,ProductUpdateDto.class);
        return productUpdateDto;
    }

    @Override
    public ApiResponse update(Long id, ProductUpdateDto productUpdateDto) {
        try{
            Product findProduct =productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product","id",id));
            if (productUpdateDto.getCategoryId() != null) {
                Category category = categoryRepository.findById(productUpdateDto.getCategoryId())
                        .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productUpdateDto.getCategoryId()));
                findProduct.setCategory(category);
            }
            findProduct.setName(productUpdateDto.getName());
            findProduct.setPrice(productUpdateDto.getPrice());
            findProduct.setDiscountPrice(productUpdateDto.getDiscountPrice());
            findProduct.setShortDescription(productUpdateDto.getShortDescription());
            findProduct.setDescription(productUpdateDto.getDescription());
            findProduct.setInformation(productUpdateDto.getInformation());
            productRepository.save(findProduct);
            return new ApiResponse("Product updated successfully",true, HttpStatus.OK);
        }catch (Exception e){
            return new ApiResponse(e.getMessage(),false,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ApiResponse remove(Long id) {
        Product findProduct =productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product","id",id));
        productRepository.save(findProduct);
        return new ApiResponse("Product updated successfully",true, HttpStatus.OK);
    }
}

