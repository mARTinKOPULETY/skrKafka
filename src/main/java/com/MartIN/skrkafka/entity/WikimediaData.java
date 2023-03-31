package com.MartIN.skrkafka.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="filtered_wikimedia")
public class WikimediaData {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private Long originalId;

    @Lob // for big data

    @Column(columnDefinition="TEXT") //sets dataType of column in  MySQL, if not,  @Lob automaticly switch it to TINYTEXT= 255 B, TEXT = 64kB
    private String wikiEventData;

    public WikimediaData(Long originalId, String wikiEventData) {
        this.originalId = originalId;
        this.wikiEventData = wikiEventData;
    }

    @Override  // To avoid: assertEquals(expectedData.toString(),  wikimediaDataList.toString() );
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        // it checks if the argument is of the type WikimediaData by comparing the classes
        // of the passed argument and this object.
        // if(!(obj instanceof WikimediaData)) return false; ---> avoid.
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        // type casting of the argument.
        WikimediaData wikimediaData = (WikimediaData) obj;
        if ((wikimediaData.getId() == this.id) &&
                (wikimediaData.getOriginalId()==this.originalId)&&
                (wikimediaData.getWikiEventData().equals(this.wikiEventData)))
            return true;

        return false;
    }
    @Override
    public String toString() {
        return "WikimediaData{" + "id=" + id + ", originalId=" + originalId + ", wikiEventData='" + wikiEventData + '\'' + '}';
    }
}
