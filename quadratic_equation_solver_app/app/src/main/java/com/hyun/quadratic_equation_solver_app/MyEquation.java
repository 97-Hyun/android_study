package com.hyun.quadratic_equation_solver_app;

import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.hyun.quadratic_equation_solver_app.databinding.ActivityMainBinding;

public class MyEquation extends BaseObservable {
    String a;
    String b;
    String c;
    ActivityMainBinding binding;

    public MyEquation(ActivityMainBinding binding) {
        this.binding = binding;
    }

    public MyEquation() {
    }

    public void setA(String a) {
        this.a = a;
    }

    public void setB(String b) {
        this.b = b;
    }

    public void setC(String c) {
        this.c = c;
    }

    @Bindable
    public String getA() {
        return a;
    }

    @Bindable
    public String getB() {
        return b;
    }

    @Bindable
    public String getC() {
        return c;
    }

    public void solveEquation(View view) {
        int a = Integer.parseInt(getA());
        int b = Integer.parseInt(getB());
        int c = Integer.parseInt(getC());

        double discriminant = b*b - 4*a*c;
        double x1, x2;

        if (discriminant > 0) {
            x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            x2 = (-b - Math.sqrt(discriminant)) / (2 * a);

            binding.textView.setText("X1 = "+x1+" X2= "+x2);
        } else if (discriminant < 0) {
            binding.textView.setText("No real roots (complex roots)");
        } else {
            x1 = -b / (2 * a);
            binding.textView.setText("x = "+x1);
        }
    }
}
