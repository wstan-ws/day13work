package sg.edu.nus.iss.day13work.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    
    @NotEmpty(message = "First Name is mandatory")
    @Size(min = 3, max = 20, message = "First Name must be between 3 to 20 characters")
    private String firstName;

    @NotEmpty(message = "Last Name is mandatory")
    @Size(min = 3, max = 20, message = "Last Name must be between 3 to 20 characters")
    private String lastName;

    @Email(message = "Invalid Email Format")
    @Size(max = 30, message = "Email length exceeded 30 characters")
    @NotBlank(message = "Email is a mandatory field")
    private String email;

    // first digit start with 8 or 9, expectinng 7 digits having digit between 0 to 9
    @Pattern(regexp = "(8|9)[0-9]{7}", message = "Invalid Phone Number")
    private String phoneNo;

    @Min(value = 1500, message = "Minimum Salary starts from 1500")
    @Max(value = 500000, message = "Maximum Salary cannot exceed 500000")
    private Integer salary;

    @Past(message = "Birth date must be a past date less than today")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Digits(fraction = 0, integer = 6, message = "Invalid Postal Code")
    @Min(value = 111111, message = "Postal Code starts from 111111")
    @Max(value = 899999, message = "Postal Code cannot exceed 899999")
    private Integer postalCode;

}
