package pl.sda.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.sda.model.Product;
import pl.sda.service.ProductService;

import javax.validation.Valid;

@Slf4j
@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/products/list")
    public String productList(ModelMap modelMap) {
        modelMap.addAttribute("products", productService.getAll());

        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        modelMap.addAttribute("currentUser", currentUser);

        return "product-list";
    }

    @GetMapping("/admin/product/create")
    public String showCreateProductForm(ModelMap modelMap) {
        modelMap.addAttribute("emptyProduct", new Product());
        return "product-create";
    }

    @PostMapping("/admin/product/save")
    public String handleNewProduct(@Valid @ModelAttribute("emptyProduct") Product product, Errors errors) {
        log.info("Handle new product: " + product);

        if (errors.hasErrors()) {
            log.error("Errors form frontend: " + errors.getFieldErrors());
            return "product-create";
        }

        productService.save(product);
        return "redirect:/products/list";
    }

    @GetMapping("/product/{title}")
    public String productDetails(@PathVariable String title, ModelMap modelMap) {
        modelMap.addAttribute("product", productService.getByTitle(title));
        return "product-details";
    }

    @GetMapping("/admin/product/edit/{title}")
    public String showEditProductForm(@PathVariable String title, ModelMap modelMap) {
        modelMap.addAttribute("product", productService.getByTitle(title));
        return "product-edit";
    }

    @PostMapping("/admin/product/update")
    public String handleUpdatedProduct(@Valid @ModelAttribute("product") Product product, Errors errors) {
        log.info("Handle product to update: " + product);

        if (errors.hasErrors()) {
            log.error("Errors form frontend: " + errors.getFieldErrors());
            return "product-edit";
        }

        productService.update(product);
        return "redirect:/product/list";
    }

    @GetMapping("/admin/product/delete/{title}")
    public String deleteProduct(@PathVariable String title) {
        log.info("Deleted product with title " + title);
        productService.deleteByTitle(title);
        return "redirect:/product/list";
    }

    @GetMapping("/product/list/params")
    public String produxtListParametrized(ModelMap modelMap,
                                          @RequestParam(defaultValue = "0") Integer pageNo,
                                          @RequestParam(defaultValue = "2") Integer pageSize,
                                          @RequestParam(defaultValue = "id") String sortBy) {

        modelMap.addAttribute("product", productService.getAll(pageNo, pageSize, sortBy));
        return "product-list";
    }

}
