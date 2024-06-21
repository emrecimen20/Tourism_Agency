package view;

import business.*;
import core.Helper;
import entity.Hotel;
import entity.Room;
import entity.RoomFeature;
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
    private DefaultTableModel tmbl_hotels = new DefaultTableModel();
    private final DefaultTableModel tmbl_seasons = new DefaultTableModel();
    private final DefaultTableModel tmbl_rooms = new DefaultTableModel();
    private HotelManager hotelManager;
    private JPopupMenu hotel_menu;
    private JPopupMenu season_menu;
    private JPopupMenu room_menu;
    private UserManager userManager;
    private Object[] col_hotel;
    private ReservationManager reservationManager;
    private HotelFeatureManager hotelFeatureManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private RoomManager roomManager;
    private RoomFeatureManager roomFeatureManager;
    ArrayList<Room> roomList;
    private final Object[] col_searched_room = new Object[]{"ID", "Otel", "Otel ID", "Sezon Başlangıcı", "Sezon Bitişi", "Pansiyon Tipi",
            "Oda Stoğu", "Oda Tipi", "Oda Özellikleri", "Toplam Gün", "Yetişkin İçin Fiyat", "Çocuk İçin Fiyat", "Toplam Fiyat"};
    private String checkIn;
    private String checkOut;
    private String child;
    private String adult;


    public EmployeeView(User user) {
        this.roomFeatureManager = new RoomFeatureManager();
        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();
        this.reservationManager = new ReservationManager();
        this.seasonManager = new SeasonManager();
        this.hotelFeatureManager = new HotelFeatureManager();
        this.pensionManager = new PensionManager();
        this.user = user;
        this.add(container);
        this.guiInitialize(1000, 500);

        if (this.user == null) {
            dispose();
        }

        loadHotelTable();
        loadHotelComponent();

        loadSeasonTable();
        loadSeasonComponent();

        loadRoomTable();
        loadRoomComponent();



    }

    private void loadRoomComponent() {
        JPopupMenu room_menu = new JPopupMenu();
        tableRowSelect(this.tbl_emp_rooms,room_menu);


        room_menu.add("Güncelle").addActionListener(e -> {
            int selectHotelId = this.getTableSelectedRow(tbl_emp_rooms, 2);
            int selectRoomId = this.getTableSelectedRow(tbl_emp_rooms, 0);

            if (this.reservationManager.getByRoomId(selectRoomId) != null) {
                Helper.showMessage("cannotUpdate");
            } else {
                RoomView roomView = new RoomView(this.roomManager.getRoomsWithDetails(selectRoomId,false).get(0), this.hotelManager.getById(selectHotelId));
                roomView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadHotelTable();
                        loadRoomTable();
                    }
                });
            }
        });

        room_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectRoomId = this.getTableSelectedRow(tbl_emp_rooms, 0);
                if (reservationManager.getByRoomId(selectRoomId) != null) {
                    Helper.showMessage("cannotDelete");
                } else {
                    if (this.roomManager.delete(selectRoomId)) {
                        roomFeatureManager.delete(selectRoomId);
                        Helper.showMessage("done");
                        loadRoomTable();

                    } else {
                        Helper.showMessage("error");
                    }
                }
            }
        });
        this.tbl_emp_rooms.setComponentPopupMenu(room_menu);
    }

    public void loadRoomTable() {
        Object[] col_room = new Object[]{"ID", "Otel", "Otel ID", "Sezon Başlangıcı", "Sezon Bitişi", "Pansiyon Tipi",
                "Oda Stoğu", "Yetişkin Gecelik Fiyat (TL)", "Çocuk Gecelik Fiyat (TL)", "Oda Tipi", "Oda Özellikleri"};

        ArrayList<Object[]> roomList = this.roomManager.getForTable(col_room.length, this.roomManager.getRoomsWithDetails(-1,false));
        createTable(this.tmbl_rooms, this.tbl_emp_rooms, col_room, roomList);
    }

    public void loadSeasonTable() {
        Object[] col_season = new Object[]{"ID", "Otel", "Sezon Başlangıcı", "Sezon Bitişi", "Sezon İsmi"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        createTable(this.tmbl_seasons, this.tbl_emp_seasons, col_season, seasonList);
    }

    private void loadSeasonComponent() {
        JPopupMenu season_menu = new JPopupMenu();

        tableRowSelect(this.tbl_emp_seasons, season_menu);


        season_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {

                int selectSeasonId = this.getTableSelectedRow(tbl_emp_seasons, 0);
                int roomId = roomManager.getBySeasonId(selectSeasonId).getRoomId();
                if (roomManager.getBySeasonId(selectSeasonId) != null) {

                    if (this.reservationManager.getByRoomId(roomId) != null) {
                        Helper.showMessage("cannotDelete");
                    } else {
                        if (roomManager.getBySeasonId(selectSeasonId) != null) {
                            this.roomFeatureManager.delete(roomId);
                            this.roomManager.deleteBySeasonId(selectSeasonId);
                        }

                        if (this.seasonManager.delete(selectSeasonId)) {
                            Helper.showMessage("done");
                            loadSeasonTable();
                            loadRoomTable();

                        } else {
                            Helper.showMessage("error");
                        }
                    }
                }
            }
        });
        this.tbl_emp_seasons.setComponentPopupMenu(season_menu);
    }

    public void loadHotelTable() {
        this.col_hotel = new Object[]{"ID", "Şehir", "Bölge", "İsim", "Sabit Telefon", "Mail", "Yıldız Sayısı",
                "Adres", "Pansiyon Tipleri", "Tesis Özellikleri", "Sezonlar"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findHotelsWithFeatures());
        createTable(this.tmbl_hotels, this.tbl_emp_hotels, col_hotel, hotelList);
    }

    private void loadHotelComponent() {
        this.hotel_menu = new JPopupMenu();

        tableRowSelect(this.tbl_emp_hotels, hotel_menu);

        this.hotel_menu.add("Yeni").addActionListener(e -> {
            HotelView hotelView = new HotelView(new Hotel());
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                    loadSeasonTable();

                }
            });
        });

        this.hotel_menu.add("Güncelle").addActionListener(e -> {
            int selectHotelId = this.getTableSelectedRow(tbl_emp_hotels, 0);

            if (this.reservationManager.getByHotelId(selectHotelId) != null) {
                Helper.showMessage("cannotDelete");
            } else {
                HotelView hotelView = new HotelView(this.hotelManager.getById(selectHotelId));
                hotelView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {

                        loadHotelTable();
                        loadSeasonTable();
                        loadRoomTable();

                    }
                });
            }
        });

        this.hotel_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectHotelId = this.getTableSelectedRow(tbl_emp_hotels, 0);
                if (!(hotelFeatureManager.getFeaturesByHotelId(selectHotelId).isEmpty())) {
                    this.hotelFeatureManager.delete(selectHotelId);
                }

                if (!(pensionManager.getPensionsByHotelId(selectHotelId).isEmpty())) {
                    this.pensionManager.delete(selectHotelId);
                }

                if (!(seasonManager.getSeasonsByHotelId(selectHotelId).isEmpty())) {
                    this.seasonManager.deleteByHotelId(selectHotelId);
                }
                if (reservationManager.getByHotelId(selectHotelId) != null) {
                    this.reservationManager.deleteByHotelId(selectHotelId);
                }

                if (this.hotelManager.delete(selectHotelId)) {
                    Helper.showMessage("done");
                    loadHotelTable();
                    loadSeasonTable();
                    loadRoomTable();

                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.hotel_menu.add("Oda Ekle").addActionListener(e -> {
            int selectHotelId = this.getTableSelectedRow(tbl_emp_hotels, 0);
            RoomView roomView = new RoomView(new Room(), this.hotelManager.getById(selectHotelId));
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                }
            });
        });

        this.tbl_emp_hotels.setComponentPopupMenu(hotel_menu);

        this.btn_room_filter.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_room_checkin, fld_room_checkout,
                    fld_room_adult_filter, fld_room_child_filter})) {
                Helper.showMessage("fillSomeAreas");
                return;
            }

            this.roomList = this.roomManager.searchForReservation(
                    this.fld_room_checkin.getText(),
                    this.fld_room_checkout.getText(),
                    this.fld_room_hotel_filter.getText(),
                    this.fld_room_city_filter.getText(),
                    Integer.parseInt(this.fld_room_adult_filter.getText()),
                    Integer.parseInt(this.fld_room_child_filter.getText())
            );

            ArrayList<Object[]> roomReservationRow = this.roomManager.getForSearchedRoomTable(col_searched_room.length, roomList);
            this.adult = fld_room_adult_filter.getText();
            this.child = fld_room_child_filter.getText();
            this.checkIn = fld_room_checkin.getText();
            this.checkOut = fld_room_checkout.getText();

            FilteredRoomView filteredRoomView = new FilteredRoomView(adult, child, checkIn, checkOut);
            filteredRoomView.loadSearchedRoomTable(roomReservationRow);
            filteredRoomView.loadSearchedRoomComponent(roomList);
            filteredRoomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable();

                }
            });

        });

    }
}
