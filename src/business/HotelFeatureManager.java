package business;

import core.Helper;
import dao.HotelFeatureDao;
import entity.HotelFeature;
import java.util.ArrayList;

public class HotelFeatureManager {

    private final HotelFeatureDao hotelFeatureDao;

    public HotelFeatureManager() {
        this.hotelFeatureDao = new HotelFeatureDao();
    }

    public HotelFeature getById(int id) {
        return this.hotelFeatureDao.getById(id);
    }

    public ArrayList<HotelFeature> findAll() {
        return this.hotelFeatureDao.findAll();
    }

    public boolean save(HotelFeature hotelFeature) {
        if (this.getById(hotelFeature.getHotelFeatureId()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.hotelFeatureDao.save(hotelFeature);
    }

    public ArrayList<HotelFeature> getFeaturesByHotelId(int id) {
        return this.hotelFeatureDao.getFeaturesByHotelId(id);
    }

    public boolean update2(HotelFeature hotelFeature) {
        if (hotelFeature.getHotelFeatureHotelId() == 0) {
            Helper.showMessage("notFound");
            return false;
        }
        return this.hotelFeatureDao.update2(hotelFeature);
    }

    public boolean delete(int hotelId) {
        if (this.getFeaturesByHotelId(hotelId) == null) {
            Helper.showMessage("Kayıt bulunamadı");
            return false;
        }
        return this.hotelFeatureDao.delete(hotelId);
    }
}

