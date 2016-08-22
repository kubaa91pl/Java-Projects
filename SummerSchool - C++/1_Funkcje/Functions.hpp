#ifndef functions_hpp
#define functions_hpp
#include <float.h>

float find_min(float number1, float number2, float number3);

float find_max(float number1, float number2, float number3);

void do_swap(float& number1, float& number2);

void do_sort(float number1, float number2, float number3 = FLT_MIN, float number4 = FLT_MIN);

float divide(float a, float b, float &div_total, float &div_remainder);

#endif
