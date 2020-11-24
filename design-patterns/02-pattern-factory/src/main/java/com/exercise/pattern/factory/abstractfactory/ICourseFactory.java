package com.exercise.pattern.factory.abstractfactory;

/**
 * 一个品牌的抽象
 */
public interface ICourseFactory {

    INote createNote();

    IVideo createVideo();
}
