package com.swj9707.twittercloneadmin.user.controller;

import com.swj9707.twittercloneadmin.user.dto.AdminUserDTO;
import com.swj9707.twittercloneadmin.user.dto.AdminUserFormDTO;
import com.swj9707.twittercloneadmin.user.service.AdminUserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adminUser")
@Slf4j
public class AdminUserController {

    private final AdminUserServiceImpl adminUserServiceImpl;

    @GetMapping("/main")
    public String adminUserPageMain(
            @PageableDefault(size = 10, sort = "userId", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(value="userName", defaultValue = "") String userName,
            Model model){

        Page<AdminUserDTO> result = adminUserServiceImpl.getList(userName, pageable);
        int startPage = Math.max(1, result.getPageable().getPageNumber() - 10);
        int endPage = Math.min(result.getTotalPages(), result.getPageable().getPageNumber() + 10);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("AdminUserList", result);
        return "adminUser/adminUserPage";
    }
    @GetMapping("/registAdminUser")
    public String registAdminUserPage(Model model){
        model.addAttribute("AdminUserFormDTO", new AdminUserFormDTO());
        return "adminUser/registAdminPage";
    }

    @PostMapping("/registAdminUser")
    public String addAdminUser(
            @AuthenticationPrincipal UserDetails userDetails,
            @ModelAttribute("AdminUserFormDTO") @Valid AdminUserFormDTO adminUserFormDTO,
            BindingResult bindingResult,
            Model model){
        String userName = userDetails.getUsername();
        adminUserFormDTO.setCreateBy(userName);
        if(bindingResult.hasErrors()){
            return "adminUser/registAdminPage";
        }
        try{
            adminUserServiceImpl.addUser(adminUserFormDTO);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "adminUser/registAdminPage";
        }
        return "redirect:/adminUser/main";
    }

    @PutMapping("/modifyUserRole")
    @ResponseBody
    public ResponseEntity<Long> modifyUserRole(@RequestParam("Id") Long userId){
        log.info("user ID " + userId);
        Long resultId = adminUserServiceImpl.modifyRole(userId);
        return ResponseEntity.ok().body(resultId);
    }

    @DeleteMapping("/deleteUser")
    @ResponseBody
    public ResponseEntity<Long> deleteUser(@RequestParam("Id") Long userId){
        adminUserServiceImpl.delete(userId);
        return ResponseEntity.ok().body(userId);
    }


}
