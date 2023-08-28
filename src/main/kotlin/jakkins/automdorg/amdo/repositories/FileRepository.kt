package jakkins.automdorg.amdo.repositories

import jakkins.automdorg.amdo.models.entities.FileEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository : JpaRepository<FileEntity, Long> {
}