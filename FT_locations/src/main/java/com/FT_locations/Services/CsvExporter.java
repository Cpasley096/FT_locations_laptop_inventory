package com.FT_locations.Services;

import com.FT_locations.Models.Laptop;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CsvExporter {

    public static void writeLaptopsToCsv(OutputStreamWriter writer, List<Laptop> laptops) throws IOException {
        CSVWriter csvWriter = new CSVWriter(writer);

        String[] header = {
                "Laptop ID", "Brand", "Model", "Status", "Price",
                "Software OS", "Software Firmware Version", "Software Firmware Updated At",
                "Hardware CPU", "Hardware GPU", "Hardware RAM", "Hardware Storage", "Hardware Screen Size",
                "Employee Name"
        };
        csvWriter.writeNext(header);

        for (Laptop laptop : laptops) {
            String softwareOS = (laptop.getSoftware() != null) ? laptop.getSoftware().getOs() : "No OS";
            String softwareFirmwareVersion = (laptop.getSoftware() != null) ? laptop.getSoftware().getFirmwareVersion() : "No Version";
            String softwareFirmwareUpdatedAt = (laptop.getSoftware() != null) ? laptop.getSoftware().getFirmwareUpdatedAt().toString() : "Not Updated";

            String hardwareCpu = (laptop.getHardware() != null) ? laptop.getHardware().getCpu() : "No CPU";
            String hardwareGpu = (laptop.getHardware() != null) ? laptop.getHardware().getGpu() : "No GPU";
            String hardwareRam = (laptop.getHardware() != null) ? String.valueOf(laptop.getHardware().getRam()) : "No RAM";
            String hardwareStorage = (laptop.getHardware() != null) ? String.valueOf(laptop.getHardware().getStorageCapacity()) : "No Storage";
            String hardwareScreenSize = (laptop.getHardware() != null) ? String.valueOf(laptop.getHardware().getScreenSize()) : "No Screen Size";

            String employeeName = (laptop.getEmployee() != null) ? laptop.getEmployee().getFullName() : "No Employee";

            csvWriter.writeNext(new String[]{
                    String.valueOf(laptop.getId()),
                    laptop.getBrand(),
                    laptop.getModel(),
                    laptop.getStatus().name(),
                    String.valueOf(laptop.getPrice()),
                    softwareOS,
                    softwareFirmwareVersion,
                    softwareFirmwareUpdatedAt,
                    hardwareCpu,
                    hardwareGpu,
                    hardwareRam,
                    hardwareStorage,
                    hardwareScreenSize,
                    employeeName
            });
        }
        csvWriter.close();
    }

    public static void exportCsv(HttpServletResponse response, List<Laptop> laptops) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);

        writeLaptopsToCsv(writer, laptops);

        byte[] csvData = outputStream.toByteArray();

        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

        String filename = String.format("laptops_report_%s.csv", currentDate);

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        response.setContentLength(csvData.length);

        response.getOutputStream().write(csvData);
        response.flushBuffer();
    }
}
