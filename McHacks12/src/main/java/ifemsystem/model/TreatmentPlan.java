package main.java.ifemsystem.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.7523.c616a4dce modeling language!*/


import java.util.*;

// line 98 "model.ump"
// line 179 "model.ump"
public class TreatmentPlan
{

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //TreatmentPlan Attributes
    private String description;

    //TreatmentPlan Associations
    private List<AssessmentDoc> assessmentDocs;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public TreatmentPlan(String aDescription)
    {
        description = aDescription;
        assessmentDocs = new ArrayList<AssessmentDoc>();
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setDescription(String aDescription)
    {
        boolean wasSet = false;
        description = aDescription;
        wasSet = true;
        return wasSet;
    }

    public String getDescription()
    {
        return description;
    }
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
    /* Code from template association_AddManyToOptionalOne */
    public boolean addAssessmentDoc(AssessmentDoc aAssessmentDoc)
    {
        boolean wasAdded = false;
        if (assessmentDocs.contains(aAssessmentDoc)) { return false; }
        TreatmentPlan existingTreatmentPlan = aAssessmentDoc.getTreatmentPlan();
        if (existingTreatmentPlan == null)
        {
            aAssessmentDoc.setTreatmentPlan(this);
        }
        else if (!this.equals(existingTreatmentPlan))
        {
            existingTreatmentPlan.removeAssessmentDoc(aAssessmentDoc);
            addAssessmentDoc(aAssessmentDoc);
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
        if (assessmentDocs.contains(aAssessmentDoc))
        {
            assessmentDocs.remove(aAssessmentDoc);
            aAssessmentDoc.setTreatmentPlan(null);
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
        while( !assessmentDocs.isEmpty() )
        {
            assessmentDocs.get(0).setTreatmentPlan(null);
        }
    }


    public String toString()
    {
        return super.toString() + "["+
                "description" + ":" + getDescription()+ "]";
    }
}
