package com.github.aoreshin.junit5.allure.steps;

import io.qameta.allure.model.Status;

/** Steps to create nested Allure steps directly in test code */
@SuppressWarnings("unchecked")
public abstract class StepWrapperSteps<T extends StepWrapperSteps<T>> {
  private static final ThreadLocal<StepWrapper> stepWrapperThreadLocal =
      ThreadLocal.withInitial(StepWrapper::new);

  public final T startStep(String stepName) {
    stepWrapper().startStep(stepName);
    return (T) this;
  }

  public final T stopStep() {
    stepWrapper().stopStep(Status.PASSED);
    return (T) this;
  }

  public final T step(String stepName, Runnable runnable) {
    stepWrapper().startStep(stepName);

    try {
      runnable.run();
      stepWrapper().stopStep(Status.PASSED);
    } catch (Exception e) {
      stepWrapper().stopStep(Status.BROKEN);
      throw e;
    }

    return (T) this;
  }

  private StepWrapper stepWrapper() {
    return stepWrapperThreadLocal.get();
  }

  /** Only for testing */
  static void setStepWrapper(StepWrapper stepWrapper) {
    stepWrapperThreadLocal.set(stepWrapper);
  }
}
