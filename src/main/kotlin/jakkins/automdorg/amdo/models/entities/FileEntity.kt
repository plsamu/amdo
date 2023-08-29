package jakkins.automdorg.amdo.models.entities

import jakarta.persistence.*
import lombok.Data
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "files")
@Data
class FileEntity() {
    constructor(originalFilename: String, calculateHash: String) : this() {
        this.filename = originalFilename
        this.hash = calculateHash
    }

    @Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen")
    @Column(name = "id", unique = true, nullable = false)
    private var id: Long? = null

    @Column(nullable = false)
    private var filename: String? = null

    @Column(nullable = false, unique = true)
    private var hash: String? = null
}