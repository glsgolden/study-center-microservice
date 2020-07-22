package com.simplex.study;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.simplex.study");

        noClasses()
            .that()
            .resideInAnyPackage("com.simplex.study.service..")
            .or()
            .resideInAnyPackage("com.simplex.study.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.simplex.study.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
