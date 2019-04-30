package codegym.service;

import codegym.model.Province;

public interface ProvinceService {
    Iterable<Province> findAll();
    Province findByID(Long id);
    void save(Province province);
    void remove(Long id);
}
