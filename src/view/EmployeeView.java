package view;

import business.HotelFeatureManager;
import business.HotelManager;
import business.ReservationManager;
import business.UserManager;
import core.Helper;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private JPanel container;
    private JButton btn_emp_logout;
    private JTabbedPane tabbedPane1;
    private JTable tbl_emp_hotels;
    private JTable tbl_emp_seasons;
    private JTextField fld_room_checkin;
    private JTextField fld_room_city_filter;
    private JTextField fld_room_checkout;
    private JTextField fld_room_adult_filter;
    private JTextField fld_room_hotel_filter;
    private JTextField fld_room_child_filter;
    private JButton btn_room_filter;
    private JTable tbl_emp_rooms;
    private JTable tbl_emp_reserv;
    private User user;
    private  DefaultTableModel tmbl_hotels = new DefaultTableModel();
    private HotelManager hotelManager;
    private JPopupMenu hotel_menu;
    private UserManager userManager;
    private Object[] col_hotel;



    public EmployeeView(User user) {
        this.hotelManager=new HotelManager();

        this.user =user;
        this.add(container);
        this.guiInitialize(1000,500);

        if(this.user==null){
            dispose();
        }

        loadHotelTable();
        loadHotelComponent();
    }

    public void loadHotelTable(){
        this.col_hotel =new Object[]{"ID", "Şehir", "Bölge", "İsim", "Sabit Telefon", "Mail", "Yıldız Sayısı",
                "Adres", "Pansiyon Tipleri", "Tesis Özellikleri", "Sezonlar"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length,this.hotelManager.findHotelsWithFeatures());
        createTable(this.tmbl_hotels,this.tbl_emp_hotels,col_hotel,hotelList);
    }

    private void loadHotelComponent() {
        this.hotel_menu =new JPopupMenu();

        tableRowSelect(this.tbl_emp_hotels,hotel_menu);

        this.hotel_menu.add("Yeni").addActionListener(e -> {

        });

        this.hotel_menu.add("Güncelle").addActionListener(e -> {

        });

        this.hotel_menu.add("Sil").addActionListener(e -> {

        });

        this.hotel_menu.add("Oda Ekle").addActionListener(e -> {

        });

        this.tbl_emp_hotels.setComponentPopupMenu(hotel_menu);

    }
}
