package com.bjtuhbxy.hb.service;

import com.bjtuhbxy.hb.dao.AdminMenuDAO;
import com.bjtuhbxy.hb.entity.AdminMenu;
import com.bjtuhbxy.hb.entity.AdminRoleMenu;
import com.bjtuhbxy.hb.entity.AdminUserRole;
import com.bjtuhbxy.hb.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminMenuService {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminMenuDAO adminMenuDAO;
    @Autowired
    private AdminMenuService adminMenuService;
    @Autowired
    private AdminUserRoleService adminUserRoleService;
    @Autowired
    private AdminRoleMenuService adminRoleMenuService;

    public List<AdminMenu> getMenusByCurrentUser() {
        // 从数据库中获取当前用户
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println(username);
        User user = userService.findByUsername(username);


        // 获得当前用户对应的所有角色的 id 列表
        List<Integer> rids = adminUserRoleService.listAllByUid(user.getId())
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());


        // 查询出这些角色对应的所有菜单项
        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rids)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDAO.findAllById(menuIds).stream().distinct().collect(Collectors.toList());

        // 处理菜单项的结构
        handleMenus(menus);
        return menus;
    }

    public void handleMenus(List<AdminMenu> menus) {
        for (AdminMenu menu : menus) {
            List<AdminMenu> children = getAllByParentId(menu.getId());
            menu.setChildren(children);
        }

        Iterator<AdminMenu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            AdminMenu menu = iterator.next();
            if (menu.getParentId() != 0) {
                iterator.remove();
            }
        }
    }

    public List<AdminMenu> getAllByParentId(int parentId) {
        return adminMenuDAO.findAllByParentId(parentId);
    }

}
