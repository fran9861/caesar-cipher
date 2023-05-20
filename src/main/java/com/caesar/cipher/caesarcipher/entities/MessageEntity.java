package com.caesar.cipher.caesarcipher.entities;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.io.Serializable;

@Entity
@Setter
@Getter
@Table(name = "message")
public class MessageEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @Column(name = "message", nullable = false, length = 100)
    private String message;

    @Column(name = "message_cipher", nullable = false, length = 100)
    private String messageCipher;

    @Column(name = "rotation_factor", nullable = false)
    private Integer rotationFactor;
}
