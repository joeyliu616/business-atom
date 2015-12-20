package com.aoe.mail.repository;

import com.aoe.mail.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by joey on 15-12-20.
 */
@Repository
public interface MailRepository extends JpaRepository<Mail,Long>{
}
