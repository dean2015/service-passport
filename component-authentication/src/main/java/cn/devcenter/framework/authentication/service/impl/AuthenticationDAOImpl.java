package cn.devcenter.framework.authentication.service.impl;

import cn.devcenter.model.authentication.Authentication;
import cn.devcenter.model.authentication.dao.AuthenticationDAO;
import cn.devcenter.model.repository.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public class AuthenticationDAOImpl implements AuthenticationDAO {
    @Override
    public Authentication save(Authentication object) {
        return null;
    }

    @Override
    public Authentication findById(Serializable serializable) {
        return null;
    }

    @Override
    public Page<Authentication> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Serializable deleteById(Serializable serializable) {
        return null;
    }

    @Override
    public Serializable update(Authentication object) {
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
