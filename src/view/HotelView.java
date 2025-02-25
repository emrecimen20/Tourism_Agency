package view;

import business.*;
import core.Helper;
import entity.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HotelView extends Layout {

    private JPanel container;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_region;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_phno;
    private JTextField fld_hotel_mail;
    private JTextField fld_hotel_star;
    private JTextField fld_hotel_address;
    private JCheckBox ucretsizOtopark_cb;
    private JCheckBox ucretsizWifi_cb;
    private JCheckBox yuzmeHavuzu_cb;
    private JCheckBox fitnessCenter_cb;
    private JCheckBox hotelConcierge_cb;
    private JCheckBox SPA_cb;
    private JCheckBox yediYirmidortOdaServisi_cb;
    private JTextField fld_winter_start;
    private JTextField fld_summer_start;
    private JTextField fld_winter_end;
    private JTextField fld_summer_end;
    private JCheckBox ultraHerseyDahil_cb;
    private JCheckBox herseyDahil_cb;
    private JCheckBox odaKahvalti_cb;
    private JCheckBox tamPansiyon_cb;
    private JCheckBox yarimPansiyon_cb;
    private JCheckBox sadeceYatak_cb;
    private JCheckBox alkolHaricFullCredit_cb;
    private JButton btn_hotel_save;
    private HotelManager hotelManager;
    private HotelFeatureManager hotelFeatureManager;
    private RoomManager roomManager;
    private PensionManager pensionManager;
    private SeasonManager seasonManager;
    private Hotel hotel;
    private ArrayList<JCheckBox> cbHotelFeatures = new ArrayList<>();
    private ArrayList<JCheckBox> cbPension = new ArrayList<>();

    public HotelView(Hotel hotel) {
        this.hotelManager = new HotelManager();
        this.hotelFeatureManager = new HotelFeatureManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.add(container);
        this.hotel = hotel;
        this.guiInitialize(700, 700);
        initializeDateFields();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        cbHotelFeatures.add(ucretsizOtopark_cb);
        cbHotelFeatures.add(hotelConcierge_cb);
        cbHotelFeatures.add(yuzmeHavuzu_cb);
        cbHotelFeatures.add(fitnessCenter_cb);
        cbHotelFeatures.add(ucretsizWifi_cb);
        cbHotelFeatures.add(SPA_cb);
        cbHotelFeatures.add(yediYirmidortOdaServisi_cb);

        cbPension.add(ultraHerseyDahil_cb);
        cbPension.add(herseyDahil_cb);
        cbPension.add(odaKahvalti_cb);
        cbPension.add(yarimPansiyon_cb);
        cbPension.add(tamPansiyon_cb);
        cbPension.add(alkolHaricFullCredit_cb);
        cbPension.add(sadeceYatak_cb);

        if (this.hotel.getHotelId() != 0) {
            this.fld_hotel_city.setText(this.hotel.getCity());
            this.fld_hotel_region.setText(this.hotel.getRegion());
            this.fld_hotel_name.setText(this.hotel.getHotelName());
            this.fld_hotel_phno.setText(this.hotel.getHotelPhno());
            this.fld_hotel_mail.setText(this.hotel.getHotelMail());
            this.fld_hotel_star.setText(this.hotel.getStar());
            this.fld_hotel_address.setText(this.hotel.getHotelAddress());

            ArrayList<HotelFeature> featuresFromDb = hotelFeatureManager.getFeaturesByHotelId(this.hotel.getHotelId());
            for (JCheckBox checkBox : cbHotelFeatures) {
                for (HotelFeature feature : featuresFromDb) {
                    if (checkBox.getText().equalsIgnoreCase(feature.getHotelFeature())) {
                        checkBox.setSelected(true);
                    }
                }
            }

            ArrayList<Pension> pensionsFromDb = pensionManager.getPensionsByHotelId(this.hotel.getHotelId());
            for (JCheckBox checkBox : cbPension) {
                for (Pension pension : pensionsFromDb) {
                    if (checkBox.getText().equalsIgnoreCase(pension.getPensionType())) {
                        checkBox.setSelected(true);
                    }
                }
            }

            ArrayList<Season> seasonsFromDb = seasonManager.getSeasonsByHotelId(this.hotel.getHotelId());
            for (Season season : seasonsFromDb) {
                if (season.getSeasonName().equals("kış")){
                    this.fld_winter_start.setText((season.getStrtDate().format(formatter)));
                    this.fld_winter_end.setText((season.getEndDate().format(formatter)));
                }else if (season.getSeasonName().equals("yaz")) {
                    this.fld_summer_start.setText((season.getStrtDate().format(formatter)));
                    this.fld_summer_end.setText((season.getEndDate().format(formatter)));
                }

            }
        }

        btn_hotel_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_hotel_city, fld_hotel_region,
                    fld_hotel_name, fld_hotel_phno, fld_hotel_mail, fld_hotel_star, fld_hotel_address})) {
                Helper.showMessage("fill");
            } else {
                boolean result;
                boolean result2 = false;
                boolean result3 = false;
                boolean result4 = false;

                this.hotel.setCity(fld_hotel_city.getText());
                this.hotel.setRegion(fld_hotel_region.getText());
                this.hotel.setHotelName(fld_hotel_name.getText());
                this.hotel.setHotelPhno(fld_hotel_phno.getText());
                this.hotel.setHotelMail(fld_hotel_mail.getText());
                this.hotel.setStar(fld_hotel_star.getText());
                this.hotel.setHotelAddress(fld_hotel_address.getText());

                ArrayList<Season> seasons = new ArrayList<>();

                String winterStartText = fld_winter_start.getText();
                String winterEndText = fld_winter_end.getText();
                if (!winterStartText.isEmpty() && !winterEndText.isEmpty()) {
                    LocalDate winterStart = LocalDate.parse(winterStartText, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    LocalDate winterEnd = LocalDate.parse(winterEndText, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    Season winterSeason = new Season();
                    winterSeason.setSeasonName("kış");
                    winterSeason.setStrtDate(winterStart);
                    winterSeason.setEndDate(winterEnd);
                    seasons.add(winterSeason);
                }

                String summerStartText = fld_summer_start.getText();
                String summerEndText = fld_summer_end.getText();
                if (!summerStartText.isEmpty() && !summerEndText.isEmpty()) {
                    LocalDate summerStart = LocalDate.parse(summerStartText, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    LocalDate summerEnd = LocalDate.parse(summerEndText, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    Season summerSeason = new Season();
                    summerSeason.setSeasonName("yaz");
                    summerSeason.setStrtDate(summerStart);
                    summerSeason.setEndDate(summerEnd);
                    seasons.add(summerSeason);
                }
                this.hotel.setSeasons(seasons);

                ArrayList<HotelFeature> selectedFeatures = new ArrayList<>();
                for (JCheckBox checkBox : cbHotelFeatures) {
                    if (checkBox.isSelected()) {
                        HotelFeature feature = new HotelFeature();
                        feature.setHotelFeature(checkBox.getText());
                        feature.setHotelFeatureHotelId(this.hotel.getHotelId());
                        selectedFeatures.add(feature);
                        this.hotel.setHotelFeatures(selectedFeatures);
                    }
                }

                ArrayList<Pension> selectedPensions = new ArrayList<>();
                for (JCheckBox checkBox : cbPension) {
                    if (checkBox.isSelected()) {
                        Pension pension = new Pension();
                        pension.setPensionType(checkBox.getText());
                        pension.setPensionHotelId(this.hotel.getHotelId());
                        selectedPensions.add(pension);
                        this.hotel.setPensionTypes(selectedPensions);
                    }
                }
                if (this.hotel.getHotelId() != 0) {
                    result = this.hotelManager.update(this.hotel);

                    for (HotelFeature feature : selectedFeatures) {
                        feature.setHotelFeatureHotelId(this.hotel.getHotelId());
                        result2 = hotelFeatureManager.update2(feature);
                    }


                    ArrayList<Integer> pensionIds = new ArrayList<>();
                    ArrayList<Integer> seasonIds = new ArrayList<>();
                    if(this.roomManager.getByHotelId(this.hotel.getHotelId()) != null){
                        ArrayList<Room> rooms =this.roomManager.getByHotelId(this.hotel.getHotelId());
                        for(Room room: rooms) {
                            pensionIds.add(room.getRoomPensionId());
                            seasonIds.add(room.getRoomSeasonId());

                        }
                    }

                    for (Season season : seasons) {
                        season.setSeasonHotelId(this.hotel.getHotelId());
                    }

                    result3 = pensionManager.update2(selectedPensions, this.hotel.getHotelId(), pensionIds);
                    result4 = seasonManager.update2(seasons, this.hotel.getHotelId(),seasonIds);

                    dispose();
                    if (result && result2 && result3 && result4) {
                        Helper.showMessage("done");

                        dispose();
                    } else {
                        Helper.showMessage("error");
                    }
                } else {
                    int hotelId = this.hotelManager.saveAndGetHotelId(this.hotel);

                    if (hotelId != 0) {
                        for (HotelFeature feature : selectedFeatures) {
                            feature.setHotelFeatureHotelId(hotelId);
                            result2 = hotelFeatureManager.save(feature);
                        }
                        for (Pension pension : selectedPensions) {
                            pension.setPensionHotelId(hotelId);
                            result3 = pensionManager.save(pension);
                        }
                        for (Season season : this.hotel.getSeasons()) {
                            season.setSeasonHotelId(hotelId);
                            result4 = seasonManager.save(season);
                        }
                    }
                    dispose();

                    if (hotelId != 0 && result2 && result3 && result4) {
                        Helper.showMessage("done");

                        dispose();
                    } else {
                        Helper.showMessage("error");
                    }
                }
            }
        });


    }

    private void initializeDateFields() {
        fld_winter_start.setText("01/01/2024");
        fld_winter_end.setText("31/05/2024");

        fld_summer_start.setText("01/06/2024");
        fld_summer_end.setText("31/12/2024");
    }


}
