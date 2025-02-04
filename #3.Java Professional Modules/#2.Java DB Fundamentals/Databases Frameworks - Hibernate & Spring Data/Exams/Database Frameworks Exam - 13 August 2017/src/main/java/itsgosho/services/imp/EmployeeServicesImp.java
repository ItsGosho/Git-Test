package itsgosho.services.imp;

import itsgosho.domain.dtos.exports.json.ProductiveEmployeeExportJSONDto;
import itsgosho.domain.dtos.imports.xml.EmployeesWrapperImportXMLDto;
import itsgosho.domain.entities.Branch;
import itsgosho.domain.entities.Employee;
import itsgosho.domain.entities.EmployeeCard;
import itsgosho.domain.entities.Product;
import itsgosho.io.impl.FileIOImpl;
import itsgosho.parser.ValidationUtil;
import itsgosho.parser.implementations.JSONParser;
import itsgosho.parser.implementations.XMLParser;
import itsgosho.parser.interfaces.ModelParser;
import itsgosho.repositories.BranchRepository;
import itsgosho.repositories.EmployeeCardRepository;
import itsgosho.repositories.EmployeeRepository;
import itsgosho.repositories.ProductRepository;
import itsgosho.services.inter.EmployeeServices;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServicesImp implements EmployeeServices {

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final EmployeeCardRepository employeeCardRepository;
    private final ProductRepository productRepository;
    private final JSONParser jsonParser;
    private final XMLParser xmlParser;
    private final ModelParser modelParser;
    private final FileIOImpl fileIO;
    private final ValidationUtil validationUtil;

    public EmployeeServicesImp(EmployeeRepository employeeRepository, BranchRepository branchRepository, EmployeeCardRepository employeeCardRepository, ProductRepository productRepository, JSONParser jsonParser, XMLParser xmlParser, ModelParser modelParser, FileIOImpl fileIO, ValidationUtil validationUtil) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.employeeCardRepository = employeeCardRepository;
        this.productRepository = productRepository;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
        this.modelParser = modelParser;
        this.fileIO = fileIO;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean save(Employee employee) {
        this.employeeRepository.save(employee);
        return true;
    }

    @Override
    public void seedDatabase() {
        if (this.employeeRepository.findAll().size() == 0) {
            EmployeesWrapperImportXMLDto employeesWrapperImportXMLDto = null;
            try {
                employeesWrapperImportXMLDto = this.xmlParser.read(EmployeesWrapperImportXMLDto.class, this.fileIO.read("xml\\input\\employees.xml"));
            } catch (IOException | JAXBException e) {
                e.printStackTrace();
            }

            employeesWrapperImportXMLDto.getEmployeeImportXMLDtos().stream().forEach(x -> {
                Branch branch = this.branchRepository.findByName(x.getBranch());
                EmployeeCard employeeCard = this.employeeCardRepository.findByNumber(x.getCard());
                if (this.validationUtil.isValid(x) && branch != null && employeeCard != null && employeeCard.getEmployee()==null) {
                    Employee employee = this.modelParser.convert(x, Employee.class);
                    employee.setBranch(branch);
                    employee.setCard(employeeCard);
                    this.save(employee);
                    employeeCard.setEmployee(employee);
                    this.employeeCardRepository.save(employeeCard);
                    System.out.println(String.format("Successfully imported Employee %s %s.", employee.getFirstName(), employee.getLastName()));
                } else {
                    System.out.println("Error: Invalid data.");
                }
            });

        }
    }

    @Override
    public void exportProductiveEmployee() {
        List<ProductiveEmployeeExportJSONDto> productiveEmployeeExportJSONDtos = new ArrayList<>();

        this.employeeRepository.exportProductiveEmployees().stream().forEach(x->{
            Object[] objects = (Object[]) x;
            ProductiveEmployeeExportJSONDto productiveEmployeeExportJSONDto = this.modelParser.convert(x,ProductiveEmployeeExportJSONDto.class);
            productiveEmployeeExportJSONDto.setFullName((String) objects[1]);
            productiveEmployeeExportJSONDto.setNumber((String) objects[3]);
            productiveEmployeeExportJSONDto.setPosition((String) objects[2]);
            productiveEmployeeExportJSONDtos.add(productiveEmployeeExportJSONDto);
        });

        try {
            String res = this.jsonParser.write(productiveEmployeeExportJSONDtos);
            this.fileIO.write(res,"json\\output\\productive-employees.json");
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
