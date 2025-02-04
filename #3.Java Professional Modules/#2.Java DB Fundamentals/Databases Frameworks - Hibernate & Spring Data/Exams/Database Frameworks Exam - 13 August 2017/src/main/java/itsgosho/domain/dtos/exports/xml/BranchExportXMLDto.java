package itsgosho.domain.dtos.exports.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "branch")
@XmlAccessorType(XmlAccessType.FIELD)
public class BranchExportXMLDto {

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "town")
    private String townName;
    @XmlAttribute(name = "total_clients")
    private long totalClients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public long getTotalClients() {
        return totalClients;
    }

    public void setTotalClients(long totalClients) {
        this.totalClients = totalClients;
    }
}
