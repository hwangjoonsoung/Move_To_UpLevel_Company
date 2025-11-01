package me.mtuc.conference.paper.repositroy;

import me.mtuc.conference.paper.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper, Long> {

}
