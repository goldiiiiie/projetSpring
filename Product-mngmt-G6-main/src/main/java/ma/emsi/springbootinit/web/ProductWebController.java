package ma.emsi.springbootinit.web;

import lombok.AllArgsConstructor;
import ma.emsi.springbootinit.entities.Product;
import ma.emsi.springbootinit.service.ServiceProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductWebController {
    ServiceProduct serviceProduct;
    @GetMapping("/index")
    String getProducts(Model model,
                       @RequestParam(defaultValue = "0") int page){
        Page<Product> productList
                = serviceProduct.getProducts(page);
        model.addAttribute("prdcts",productList);
        model.addAttribute("title",
                "Liste des produits");
        model.addAttribute("count",
                productList.getTotalElements());

        model.addAttribute("currentPage"
                ,productList.getNumber());

        int[] listPages
                = new int[productList.getTotalPages()];
        for (int i =0; i<listPages.length; i++)
            listPages[i] = i;
        model.addAttribute("listPages",
                listPages);
        return "products"; //Nom de la page HTML (templates)
    }

//    @GetMapping("/delete")
//    String deleteProductById(@RequestParam Long id){
//        System.out.println("PRDCT TO BE DELETE: " + id);
//        serviceProduct.deleteProduct(id);
//        return "redirect:/index";
//    }

//    @GetMapping("/delete")
//    public RedirectView deleteProductById(@RequestParam Long id, RedirectAttributes redirectAttributes) {
//        try {
//            serviceProduct.deleteProduct(id);
//            redirectAttributes.addFlashAttribute("success", "Product deleted successfully");
//            return new RedirectView("/index", true); // Redirection vers la page index
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error", "Failed to delete product");
//            return new RedirectView("/", true); // Redirection vers une autre page si nécessaire
//        }
//    }


    @GetMapping("/delete")
    String deleteProductById(@RequestParam Long id , RedirectAttributes redirectAttributes) {
        System.out.println("PRDCT TO BE DELETE: " + id);
        try {
            serviceProduct.deleteProduct(id);
            redirectAttributes.addFlashAttribute("deleted" , "deleted product successfully");
            return "redirect:/index";
        }catch (Exception exception){
            redirectAttributes.addFlashAttribute("error" , "Error while deleting product");
            return "redirect:/index";
        }
    }


    @PostMapping("/edit")
    String editProductByPrice(@RequestParam Float price,
                               @RequestParam Long id){
         serviceProduct.editProductPrice(id, price);
        return "redirect:/index";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        serviceProduct.addProduct(product);
        return "redirect:/index";
    }

//    @GetMapping("/add") // Changé de "/save" à "/add"
//    public String add(Model model) {
//        Product product = new Product();
//        model.addAttribute("product", product);
//        return "redirect:/index";
//    }
//
//    @GetMapping("/close")
//    public String close() {
//        return "redirect:/index";
//    }

    @GetMapping("/index/shownewProductform")
    public String shownewProductform(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "add";
    }


    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product,@RequestParam(defaultValue = "0") int page) {
        serviceProduct.saveProduct(product);
        Page<Product> productList
                    = serviceProduct.getProducts(page);

        // Récupérer le nombre total de produits
        long totalProducts = productList.getTotalElements();

        // Calculer le nombre total de pages
        int pageSize = 10; // Taille de pagination
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        // Rediriger l'utilisateur vers la dernière page
        return "redirect:/index?page=" + (totalPages - 1);
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id , Model model) {
        Product product = serviceProduct.getProductById(id);
        model.addAttribute("product", product);
        return "update";
    }

    @GetMapping("/getAll")
    public String getAll(Model model, @Param("keyword") String keyword){
        List<Product> products=null;
        if (keyword != null && !keyword.isEmpty()) {
            products = serviceProduct.findallProducts(keyword);

        }else
            serviceProduct.getProducts(0);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "products";
    }






}
