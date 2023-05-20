package com.caesar.cipher.caesarcipher.repository;

import com.caesar.cipher.caesarcipher.entities.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {


    Optional<MessageEntity> findById(Integer id);
    Page<MessageEntity> findAll(Pageable pageable);

}
