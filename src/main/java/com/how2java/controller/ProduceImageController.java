package com.how2java.controller;

import com.how2java.pojo.Product;
import com.how2java.pojo.ProductImage;
import com.how2java.service.ProductImageService;
import com.how2java.service.ProductService;
import com.how2java.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ProduceImageController {
    @Autowired
    ProductImageService productImageService;
    @Autowired
    ProductService productService;

    @RequestMapping("/admin_productImage_list")
    public String list(int pid, Model model) {
        Product product = productService.getOne(pid);
        List<ProductImage> pisSingle = productImageService.list(pid, productImageService.single);
        List<ProductImage> pisDetail = productImageService.list(pid, productImageService.detail);
        model.addAttribute("p", product);
        model.addAttribute("pisSingle", pisSingle);
        model.addAttribute("pisDetail", pisDetail);
        return "admin/listProductImage";
    }

    @RequestMapping("/admin_productImage_add")
    public String add(ProductImage product, HttpSession session, MultipartFile image) {
        productImageService.add(product);
        String fileName = product.getId() + ".jpg";
        String imageFolder;
        String imageFolder_small = null;
        String imageFolder_middle = null;
        if (productImageService.single.equals(product.getType())) {
            imageFolder = session.getServletContext().getRealPath("/img/productSingle");
            imageFolder_middle = session.getServletContext().getRealPath("/img/productSingle_middle");
            imageFolder_small = session.getServletContext().getRealPath("/img/productSingle_small");
        } else {
            imageFolder = session.getServletContext().getRealPath("/img/productDetail");
        }
        File file = new File(imageFolder, fileName);
        file.getParentFile().mkdirs();
        try {
            image.transferTo(file);
            BufferedImage i = ImageUtil.change2jpg(file);
            ImageIO.write(i, "jpg", file);
            if (productImageService.single.equals(product.getType())) {
                File file_middle = new File(imageFolder_middle, fileName);
                File file_small = new File(imageFolder_small, fileName);
                ImageUtil.resizeImage(file, 56, 56, file_small);
                ImageUtil.resizeImage(file, 217, 190, file_middle);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin_productImage_list?pid=" + product.getPid();
    }

    @RequestMapping("/admin_productImage_delete")
    public String delete(int id,HttpSession session){
        ProductImage productImage=productImageService.getOne(id);
        String imageFolder;
        String imageFolder_small=null;
        String imageFolder_middle=null;
        if(productImage.getType().equals(productImageService.single)){
            imageFolder=session.getServletContext().getRealPath("/img/productSingle");
            imageFolder_small=session.getServletContext().getRealPath("/img/productSingle_small");
            imageFolder_middle=session.getServletContext().getRealPath("/img/productSingle_middle");
            File file=new File(imageFolder,id+".jpg");
            File file_small=new File(imageFolder_small,id+".jpg");
            File file_middle=new File(imageFolder_middle,id+".jpg");
            file.delete();
            file_middle.delete();
            file_small.delete();
        }else {
            imageFolder=session.getServletContext().getRealPath("/img/productDetail");
            File f=new File(imageFolder,id+".jpg");
            f.delete();
        }
        productImageService.delete(id);
        return "redirect:/admin_productImage_list?pid="+productImage.getPid();
    }


}
