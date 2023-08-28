package jakkins.automdorg.amdo.models.entities

import jakarta.persistence.*
import lombok.Data

@Entity
@Table(name = "files")
@Data
class FileEntity() {
    constructor(originalFilename: String, calculateHash: String) : this() {
        this.filename = originalFilename
        this.hash = calculateHash
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @Column(nullable = false)
    var filename: String? = null

    @Column(nullable = false, unique = true)
    var hash: String? = null
}