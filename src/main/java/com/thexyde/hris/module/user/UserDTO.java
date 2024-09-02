package com.thexyde.hris.module.user;

import java.util.Objects;
import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password should have at least 8 characters")
    private String password;

    private UUID clientId;

    private String roleId;

    private UUID departmentId;

    private UUID divisionId;

    private UUID jobUnitId;

    private UUID jobLevelId;

    private UUID jobTitleId;

    public UserDTO() {
    }

    public UserDTO(String name, String email, String password, UUID clientId, String roleId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.clientId = clientId;
        this.roleId = roleId;
    }

    public UserDTO(String name, String email, String password, UUID clientId, String roleId, UUID departmentId,
            UUID divisionId, UUID jobUnitId, UUID jobLevelId, UUID jobTitleId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.clientId = clientId;
        this.roleId = roleId;
        this.departmentId = departmentId;
        this.divisionId = divisionId;
        this.jobUnitId = jobUnitId;
        this.jobLevelId = jobLevelId;
        this.jobTitleId = jobTitleId;
    }

    public UUID getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(UUID departmentId) {
        this.departmentId = departmentId;
    }

    public UUID getDivisionId() {
        return this.divisionId;
    }

    public void setDivisionId(UUID divisionId) {
        this.divisionId = divisionId;
    }

    public UUID getJobUnitId() {
        return this.jobUnitId;
    }

    public void setJobUnitId(UUID jobUnitId) {
        this.jobUnitId = jobUnitId;
    }

    public UUID getJobLevelId() {
        return this.jobLevelId;
    }

    public void setJobLevelId(UUID jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    public UUID getJobTitleId() {
        return this.jobTitleId;
    }

    public void setJobTitleId(UUID jobTitleId) {
        this.jobTitleId = jobTitleId;
    }

    public UserDTO departmentId(UUID departmentId) {
        setDepartmentId(departmentId);
        return this;
    }

    public UserDTO divisionId(UUID divisionId) {
        setDivisionId(divisionId);
        return this;
    }

    public UserDTO jobUnitId(UUID jobUnitId) {
        setJobUnitId(jobUnitId);
        return this;
    }

    public UserDTO jobLevelId(UUID jobLevelId) {
        setJobLevelId(jobLevelId);
        return this;
    }

    public UserDTO jobTitleId(UUID jobTitleId) {
        setJobTitleId(jobTitleId);
        return this;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getClientId() {
        return this.clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public UserDTO name(String name) {
        setName(name);
        return this;
    }

    public UserDTO email(String email) {
        setEmail(email);
        return this;
    }

    public UserDTO password(String password) {
        setPassword(password);
        return this;
    }

    public UserDTO clientId(UUID clientId) {
        setClientId(clientId);
        return this;
    }

    public UserDTO roleId(String roleId) {
        setRoleId(roleId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDTO)) {
            return false;
        }
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(name, userDTO.name) && Objects.equals(email, userDTO.email)
                && Objects.equals(password, userDTO.password) && Objects.equals(clientId, userDTO.clientId)
                && Objects.equals(roleId, userDTO.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password, clientId, roleId);
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", clientId='" + getClientId() + "'" +
                ", roleId='" + getRoleId() + "'" +
                "}";
    }

}