package org.cash.surveysuam.model.survey;

public enum QuestionType {
    CHECKBOX, // One to many answers
    MULTIPLE_CHOICE, // Single Answer (One option)
    TEXT, // User can type anything
    MULTIPLE_CHOICE_AND_TEXT, // Single option or something typed by the user
    CHECKBOX_AND_TEXT; // One to many answer, including text
}