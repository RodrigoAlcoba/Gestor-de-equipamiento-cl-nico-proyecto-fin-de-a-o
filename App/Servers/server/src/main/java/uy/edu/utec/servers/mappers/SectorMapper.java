package uy.edu.utec.servers.mappers;

import uy.edu.utec.servers.dtos.SectorDTO;
import uy.edu.utec.servers.entidades.Sector;

import java.util.ArrayList;
import java.util.List;

public class SectorMapper {
    public static SectorDTO toDTO(Sector sector) {
        return new SectorDTO(sector.getIdSector(), sector.getNombreSector());
    }
    public static List<SectorDTO> toDTO(List<Sector> sectores) {
        List<SectorDTO> sectorDTOList = new ArrayList<>();
        for (Sector sector : sectores) {
            SectorDTO sectorDTO = new SectorDTO(
                    sector.getIdSector(),
                    sector.getNombreSector()
            );
            sectorDTOList.add(sectorDTO);
        }
        return sectorDTOList;
    }
}
