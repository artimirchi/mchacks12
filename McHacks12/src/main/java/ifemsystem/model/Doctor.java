package main.java.ifemsystem.model;


/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

// line 45 "model.ump"
// line 135 "model.ump"
public class Doctor extends Employee
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Doctor Associations
    private List<AssessmentDoc> assessmentDocs;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public Doctor(String aEmail, String aPassword, IFEMs aIFEMs, Person aPerson, int aEmployeeID)
    {
        super(aEmail, aPassword, aIFEMs, aPerson, aEmployeeID);
        assessmentDocs = new ArrayList<AssessmentDoc>();
    }

    //------------------------
    // INTERFACE
    //------------------------
    /* Code from template association_GetMany */
    public AssessmentDoc getAssessmentDoc(int index)
    {
        AssessmentDoc aAssessmentDoc = assessmentDocs.get(index);
        return aAssessmentDoc;
    }

    public List<AssessmentDoc> getAssessmentDocs()
    {
        List<AssessmentDoc> newAssessmentDocs = Collections.unmodifiableList(assessmentDocs);
        return newAssessmentDocs;
    }

    public int numberOfAssessmentDocs()
    {
        int number = assessmentDocs.size();
        return number;
    }

    public boolean hasAssessmentDocs()
    {
        boolean has = assessmentDocs.size() > 0;
        return has;
    }

    public int indexOfAssessmentDoc(AssessmentDoc aAssessmentDoc)
    {
        int index = assessmentDocs.indexOf(aAssessmentDoc);
        return index;
    }
    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfAssessmentDocs()
    {
        return 0;
    }
    /* Code from template association_AddManyToOne */
    public AssessmentDoc addAssessmentDoc(String aDescription, HospitalStay aHospitalStay, IFEMs aIFEMs)
    {
        return new AssessmentDoc(aDescription, aHospitalStay, this, aIFEMs);
    }

    public boolean addAssessmentDoc(AssessmentDoc aAssessmentDoc)
    {
        boolean wasAdded = false;
        if (assessmentDocs.contains(aAssessmentDoc)) { return false; }
        Doctor existingDoctor = aAssessmentDoc.getDoctor();
        boolean isNewDoctor = existingDoctor != null && !this.equals(existingDoctor);
        if (isNewDoctor)
        {
            aAssessmentDoc.setDoctor(this);
        }
        else
        {
            assessmentDocs.add(aAssessmentDoc);
        }
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeAssessmentDoc(AssessmentDoc aAssessmentDoc)
    {
        boolean wasRemoved = false;
        //Unable to remove aAssessmentDoc, as it must always have a doctor
        if (!this.equals(aAssessmentDoc.getDoctor()))
        {
            assessmentDocs.remove(aAssessmentDoc);
            wasRemoved = true;
        }
        return wasRemoved;
    }
    /* Code from template association_AddIndexControlFunctions */
    public boolean addAssessmentDocAt(AssessmentDoc aAssessmentDoc, int index)
    {
        boolean wasAdded = false;
        if(addAssessmentDoc(aAssessmentDoc))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfAssessmentDocs()) { index = numberOfAssessmentDocs() - 1; }
            assessmentDocs.remove(aAssessmentDoc);
            assessmentDocs.add(index, aAssessmentDoc);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveAssessmentDocAt(AssessmentDoc aAssessmentDoc, int index)
    {
        boolean wasAdded = false;
        if(assessmentDocs.contains(aAssessmentDoc))
        {
            if(index < 0 ) { index = 0; }
            if(index > numberOfAssessmentDocs()) { index = numberOfAssessmentDocs() - 1; }
            assessmentDocs.remove(aAssessmentDoc);
            assessmentDocs.add(index, aAssessmentDoc);
            wasAdded = true;
        }
        else
        {
            wasAdded = addAssessmentDocAt(aAssessmentDoc, index);
        }
        return wasAdded;
    }

    public void delete()
    {
        for(int i=assessmentDocs.size(); i > 0; i--)
        {
            AssessmentDoc aAssessmentDoc = assessmentDocs.get(i - 1);
            aAssessmentDoc.delete();
        }
        super.delete();
    }

}
