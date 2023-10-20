package com.crud.nomad.mapper;

import com.crud.nomad.domain.Medicine;
import com.crud.nomad.domain.dto.MedicineDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MedicineMapper {

    public Medicine mapToMedicine(final MedicineDto medicineDto) {
        Medicine medicine = new Medicine();
        medicine.setMedicineId(medicineDto.getMedicineId());
        medicine.setMedicineName(medicineDto.getMedicineName());
        medicine.setDesignation(medicineDto.getDesignation());
        medicine.setMedType(medicineDto.getMedType());
        medicine.setExpiryDate(medicineDto.getExpiryDate());
        return medicine;
    }

    public MedicineDto mapToMedicineDto(final Medicine medicine) {
        MedicineDto medicineDto = new MedicineDto();
        medicineDto.setMedicineId(medicine.getMedicineId());
        medicineDto.setMedicineName(medicine.getMedicineName());
        medicineDto.setDesignation(medicine.getDesignation());
        medicineDto.setMedType(medicine.getMedType());
        medicineDto.setExpiryDate(medicine.getExpiryDate());
        return medicineDto;
    }

    public List<MedicineDto> mapToMedicineDtoList(final List<Medicine> medicineList) {
        List<MedicineDto> medicineDtoList = new ArrayList<>();
        for (Medicine medicine : medicineList) {
            medicineDtoList.add(mapToMedicineDto(medicine));
        }
        return medicineDtoList;
    }
}
