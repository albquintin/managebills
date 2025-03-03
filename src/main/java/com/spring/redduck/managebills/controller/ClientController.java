package com.spring.redduck.managebills.controller;

import com.spring.redduck.managebills.dto.ClientDto;
import com.spring.redduck.managebills.dto.SupplierDto;
import com.spring.redduck.managebills.service.impl.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ClientController {

    private ClientServiceImpl clientService;
    public ClientController(ClientServiceImpl clientService){
        this.clientService = clientService;
    }
    @GetMapping("/clients/clients")
    public String clients(Model model){
        List<ClientDto> clients = clientService.findAllClientsWithAccumulatedQuantity();
        model.addAttribute("clients", clients);
        String year = "2025";
        model.addAttribute("year", year);
        return "/clients/clients";
    }
    @GetMapping("/clients/clients/newclient")
    public String newClientForm(Model model){
        ClientDto clientDto = new ClientDto();
        model.addAttribute("client", clientDto);
        return "/clients/create_client";
    }

    @PostMapping("clients/clients")
    public String createClient(@Valid @ModelAttribute("client") ClientDto clientDto,
                                 BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("client", clientDto);
            return "/clients/create_client";
        }
        clientService.createClient(clientDto);
        return "redirect:/clients/clients";
    }

    @GetMapping("clients/clients/edit/{clientId}")
    public String editClientForm(@PathVariable("clientId") Long clientId, Model model){
        ClientDto clientDto = clientService.findClientById(clientId);
        model.addAttribute("client", clientDto);
        return "clients/edit_client";
    }

    @PostMapping("clients/clients/{clientId}")
    public String updateClient(@PathVariable("clientId") Long clientId,
                                 @Valid @ModelAttribute("supplier") ClientDto clientDto,
                                 BindingResult result, Model model){
        if(result.hasErrors()){
            clientDto.setId(clientId);
            model.addAttribute("client", clientDto);
            return "clients/edit_client";
        }
        clientDto.setId(clientId);
        clientService.updateClient(clientDto);
        return "redirect:/clients/clients";
    }
    @GetMapping("/clients/searchQuantityByYear")
    public String searchQuantityByYear(@RequestParam(value = "year") String year, Model model){
        List<ClientDto> clients = clientService.findQuantitiesByYear(Long.parseLong(year));
        model.addAttribute("clients", clients);
        model.addAttribute("year", year);
        return "/clients/clients";
    }

    @GetMapping("/clients/client347form")
    public String print347Form(Model model){
        List<ClientDto> clients = new ArrayList<>();
        model.addAttribute("clients", clients);
        model.addAttribute("searchDone", false);
        return "clients/client_347_form";
    }

    @GetMapping("/clients/fillFormByYear")
    public String fillFormByYear(@RequestParam(value = "year") String year, Model model){
        List<ClientDto> clients = clientService.findClientsFor347Form(Long.parseLong(year));
        model.addAttribute("clients", clients);
        model.addAttribute("year", year);
        model.addAttribute("searchDone", true);
        return "clients/client_347_form";
    }
}
