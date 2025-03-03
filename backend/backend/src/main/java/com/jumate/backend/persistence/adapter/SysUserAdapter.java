package com.jumate.backend.persistence.adapter;

import com.jumate.backend.application.exception.ResourceNotFoundException;
import com.jumate.backend.domain.mapper.SysUserMapper;
import com.jumate.backend.domain.model.SysUser;
import com.jumate.backend.persistence.jpa.SysUserJpaRepository;
import com.jumate.backend.persistence.repository.SysUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SysUserAdapter implements SysUserRepository {
    private final SysUserJpaRepository sysUserJpaRepository;
    private final SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserById(Long id) {
        return sysUserMapper.entityToModel(sysUserJpaRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("id not found : " + id)
        ));
    }

    @Override
    public SysUser save(SysUser sysUser) {
        return sysUserMapper.entityToModel(sysUserJpaRepository.save(sysUserMapper.modelToEntity(sysUser)));
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.entityToModel(sysUserJpaRepository.findByUsername(username).orElseThrow(
                ()-> new ResourceNotFoundException("username not found : " + username)
        ));
    }

    @Override
    public boolean isUsernameAlreadyExists(String username) {
        return sysUserJpaRepository.existsByUsername(username);
    }
}
