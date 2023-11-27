package com.spring.redduck.managebills.controller;

import com.spring.redduck.managebills.dto.BillDto;
import com.spring.redduck.managebills.dto.SupplierDto;
import com.spring.redduck.managebills.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SupplierController {

    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService){
        this.supplierService = supplierService;
    }
    @GetMapping("/suppliers/suppliers")
    public String suppliers(Model model){
        List<SupplierDto> suppliers = supplierService.findAllSuppliersWithAccumulatedQuantity();
        model.addAttribute("suppliers", suppliers);
        return "/suppliers/suppliers";
    }

    @GetMapping("/suppliers/suppliers/newsupplier")
    public String newSupplierForm(Model model){
        SupplierDto supplierDto = new SupplierDto();
        model.addAttribute("supplier", supplierDto);
        return "/suppliers/create_supplier";
    }

    @PostMapping("suppliers/suppliers")
    public String createSupplier(@Valid @ModelAttribute("supplier") SupplierDto supplierDto,
                                 BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("supplier", supplierDto);
            return "/suppliers/create_supplier";
        }
        supplierService.createSupplier(supplierDto);
        return "redirect:/suppliers/suppliers";
    }

    @GetMapping("suppliers/suppliers/edit/{supplierId}")
    public String editSupplierForm(@PathVariable("supplierId") Long supplierId, Model model){
        SupplierDto supplierDto = supplierService.findSupplierById(supplierId);
        model.addAttribute("supplier", supplierDto);
        return "suppliers/edit_supplier";
    }

    @PostMapping("suppliers/suppliers/{supplierId}")
    public String updateSupplier(@PathVariable("supplierId") Long supplierId,
                                 @Valid @ModelAttribute("supplier") SupplierDto supplierDto,
                                 BindingResult result, Model model){
        if(result.hasErrors()){
            supplierDto.setId(supplierId);
            model.addAttribute("supplier", supplierDto);
            return "suppliers/edit_supplier";
        }
        supplierDto.setId(supplierId);
        supplierService.updateSupplier(supplierDto);
        return "redirect:/suppliers/suppliers";
    }

}
