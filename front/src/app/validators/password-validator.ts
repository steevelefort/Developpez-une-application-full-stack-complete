import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function passwordValidator(): ValidatorFn {
  const rx = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\W_]).{8,}$/;
  return (control: AbstractControl): ValidationErrors | null => {
    const v = control.value ?? '';

    if (v.length === 0) return null;

    const length  = v.length >= 8;
    const lower   = /[a-z]/.test(v);
    const upper   = /[A-Z]/.test(v);
    const digit   = /\d/.test(v);
    const special = /[\W_]/.test(v);

    if (length && lower && upper && digit && special) return null;
    return { weakPassword: true };
  };
}
