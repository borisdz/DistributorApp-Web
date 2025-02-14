package mk.ukim.finki.db.distributorapp.driver;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryEndDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryFullDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliverySimpleDto;
import mk.ukim.finki.db.distributorapp.delivery.dto.DeliveryStartDto;
import mk.ukim.finki.db.distributorapp.driver.dto.DriverDto;
import mk.ukim.finki.db.distributorapp.delivery.DeliveryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DeliveryService deliveryService;

    @Override
    public DriverDto findById(Long id) {
        return this.driverRepository.findDriverById(id);
    }

    @Override
    public Integer create(DriverDto driverDto) {
        return this.driverRepository.create(
                driverDto.getId(),
                driverDto.getVehId());
    }

    @Override
    public Integer edit(DriverDto driverDto) {
        return this.driverRepository.edit(
                driverDto.getId(),
                driverDto.getVehId()
        );
    }

    @Override
    public void deleteById(Long id) {
        this.driverRepository.deleteById(id);
    }

    @Override
    public List<DeliverySimpleDto> getNewAssignedDeliveries(Long driverId) {
        return this.driverRepository.activeAssignedDeliveries(driverId);
    }

    @Override
    public List<DeliverySimpleDto> getFinishedAssignedDeliveries(Long driverId) {
        return this.driverRepository.finishedAssignedDeliveries(driverId);
    }

    @Override
    public List<DeliverySimpleDto> getOngoingDeliveries(Long driverId) {
        return this.driverRepository.getOngoingDeliveries(driverId);
    }

    @Override
    @Transactional
    public void startDelivery(DeliveryStartDto delivery) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        DeliveryFullDto deliveryDto = this.deliveryService.findDeliveryById(delivery.getId());
        deliveryDto.setDelStartKm(delivery.getDelStartKm());
        deliveryDto.setDelStartTime(LocalTime.now().format(formatter));
        deliveryDto.setDelStatusId((short) 3);
        this.deliveryService.edit(deliveryDto);
    }

    @Override
    @Transactional
    public void endDelivery(DeliveryEndDto delivery) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        DeliveryFullDto deliveryDto = this.deliveryService.findDeliveryById(delivery.getId());
        deliveryDto.setDelEndKm(delivery.getDelEndKm());
        deliveryDto.setDelEndTime(LocalTime.now().format(formatter));
        deliveryDto.setDelStatusId((short) 4);
        this.deliveryService.edit(deliveryDto);
    }
}
