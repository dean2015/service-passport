package cn.devcenter.framework.authentication.service.impl;

import cn.devcenter.model.authentication.AppModel;
import cn.devcenter.model.authentication.dao.AppDAO;
import cn.devcenter.model.repository.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public class AppDAOImpl implements AppDAO {

    @Override
    public AppModel save(AppModel object) {
        return null;
    }

    @Override
    public AppModel findById(Serializable serializable) {
        return null;
    }

    @Override
    public Page<AppModel> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Serializable deleteById(Serializable serializable) {
        return null;
    }

    @Override
    public Serializable update(AppModel object) {
        return null;
    }

    @Override
    public Boolean existsById(Serializable serializable) {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }
}
