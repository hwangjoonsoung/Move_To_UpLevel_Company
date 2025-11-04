package me.mtuc.conference.conf.paper.repositroy;

import me.mtuc.conference.conf.paper.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper, Long> {

}
