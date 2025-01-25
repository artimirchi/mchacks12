package ifemsystem.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/



// line 103 "model.ump"
// line 184 "model.ump"
public class MedicalRecord
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //MedicalRecord Associations
    private Patient patient;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public MedicalRecord(Patient aPatient)
    {
        boolean didAddPatient = setPatient(aPatient);
        if (!didAddPatient)
        {
            throw new RuntimeException("Unable to create medicalRecord due to patient. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
        }
    }

    //------------------------
    // INTERFACE
    //------------------------
    /* Code from template association_GetOne */
    public Patient getPatient()
    {
        return patient;
    }
    /* Code from template association_SetOneToOptionalOne */
    public boolean setPatient(Patient aNewPatient)
    {
        boolean wasSet = false;
        if (aNewPatient == null)
        {
            //Unable to setPatient to null, as medicalRecord must always be associated to a patient
            return wasSet;
        }

        MedicalRecord existingMedicalRecord = aNewPatient.getMedicalRecord();
        if (existingMedicalRecord != null && !equals(existingMedicalRecord))
        {
            //Unable to setPatient, the current patient already has a medicalRecord, which would be orphaned if it were re-assigned
            return wasSet;
        }

        Patient anOldPatient = patient;
        patient = aNewPatient;
        patient.setMedicalRecord(this);

        if (anOldPatient != null)
        {
            anOldPatient.setMedicalRecord(null);
        }
        wasSet = true;
        return wasSet;
    }

    public void delete()
    {
        Patient existingPatient = patient;
        patient = null;
        if (existingPatient != null)
        {
            existingPatient.setMedicalRecord(null);
        }
    }

}
