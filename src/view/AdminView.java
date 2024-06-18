package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JPanel container;
    private JButton btn_user_logout;
    private JTabbedPane tabbedPane1;
    private JTable tbl_users;
    private JLabel lbl_welcome;
    private User user;
    private final DefaultTableModel tmbl_users = new DefaultTableModel();
    private final UserManager userManager;
    private Object[] col_user;
    private JPopupMenu user_menu;

    public AdminView(User user) {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitialize(700, 500);
        this.user = user;
        if (this.user == null) {
            dispose();
        }


        loadUserTable();
        loadUserComponent();

        loadComponent();


    }

    public void loadUserTable() {
        this.col_user = new Object[]{"ID", "Rol", "İsim Soyisim", "Şifre"};
        ArrayList<Object[]> userList = this.userManager.getForTable(col_user.length, this.userManager.findAll());
        createTable(this.tmbl_users, this.tbl_users, col_user, userList);
    }

    private void loadUserComponent() {

        this.user_menu=new JPopupMenu();
        tableRowSelect(this.tbl_users,user_menu);

        this.user_menu.add("Yeni").addActionListener(e -> {
            UserView userView = new UserView(new User());
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });

        this.user_menu.add("Güncelle").addActionListener(e -> {
            int selectedUserId = this.getTableSelectedRow(tbl_users, 0);
            UserView userView = new UserView(this.userManager.getById(selectedUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();

                }
            });
        });

        this.user_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUsertId = this.getTableSelectedRow(tbl_users, 0);
                if (this.userManager.delete(selectUsertId)) {
                    Helper.showMessage("done");
                    loadUserTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
        this.tbl_users.setComponentPopupMenu(user_menu);
    }

    private void loadComponent() {
        this.btn_user_logout.addActionListener(e -> {
            dispose();
            new LoginView();
        });
    }
}

