package com.github.aoreshin.junit5.allure.steps.steprepositories;

import com.github.aoreshin.junit5.allure.steps.StepRepository;
import io.qameta.allure.Step;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

@StepRepository
public interface TableSortingStepRepository<T> {
  @Step("Проверка что колонка {column} отсортирована по {way}")
  default T checkColumnSorted(final String column, final Sorting way, final Comparator comparator) {
    throw new NotImplementedException();
  }

  @Step("Проверка что колонка с датами {column} отсортирована по {way}")
  default T checkDateColumnSorted(final String column, final Sorting way) {
    throw new NotImplementedException();
  }

  @Step("Проверка что колонка с датами {column} отсортирована по {way}")
  default T checkDateColumnSorted(
      final String column,
      final Sorting way,
      final DateTimeFormatter formatter,
      final Comparator<LocalDate> comparator) {
    throw new NotImplementedException();
  }

  enum Sorting {
    ASCENDING(),
    DESCENDING()
  }
}
