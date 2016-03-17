def frac(licznik, mianownik):
    if mianownik == 0:
        return "Mianownik nie moze byc 0"
    elif(licznik%mianownik==0):
        licznik=licznik/mianownik
        mianownik =1

    L=[licznik,mianownik]
    return L


def add_frac(frac1, frac2):             # frac1 + frac2
   licznik = (frac1[0]*frac2[1]) + (frac2[0]*frac1[1])
   mianownik = (frac1[1]*frac2[1])
   return frac(licznik,mianownik)

def sub_frac(frac1, frac2):        # frac1 - frac2
   licznik = (frac1[0]*frac2[1]) - (frac2[0]*frac1[1])
   mianownik = (frac1[1]*frac2[1])
   return frac(licznik,mianownik)

def mul_frac(frac1, frac2):       # frac1 * frac2
    licznik = frac1[0]*frac2[0]
    mianownik = frac1[1]*frac2[1]
    return frac(licznik,mianownik)

def div_frac(frac1, frac2):        # frac1 / frac2
    licznik = frac1[0] * frac2[1]
    mianownik = frac1[1] * frac2[0]
    return frac(licznik,mianownik)

def is_positive(frac):           # bool, czy dodatni
    if frac[0] > 0 and frac[1] > 0:
        return True
    elif frac[0] < 0 and frac[1] < 0:
        return True
    else:
        return False

def is_zero(frac):                 # bool, typu [0, x]
    if frac[0] == 0:
        return True
    else:
        return False

def cmp_frac(frac1, frac2):        # -1 | 0 | +1
    if frac2float(frac1) < frac2float(frac2):
        return -1
    elif frac2float(frac1) == frac2float(frac2):
        return 0
    elif frac2float(frac1) > frac2float(frac2):
        return 1

def frac2float(frac):              # konwersja do float
    return frac[0] / float(frac[1])

f1 = [-1, 2]                  # -1/2
f2 = [0, 1]                   # zero
f3 = [3, 1]                   # 3
f4 = [6, 2]                   # 3 (niejednoznacznosc)
f5 = [0, 2]                   # zero (niejednoznacznosc)

import unittest

class TestFractions(unittest.TestCase):

    def setUp(self):
        self.zero = [0, 1]
    def test_add_frac(self):
        self.assertEqual(add_frac([1, 2], [1, 3]), [5, 6])
    def test_sub_frac(self):
        self.assertEqual(sub_frac([1, 2], [1, 4]), [1, 4])
    def test_mul_frac(self):
        self.assertEqual(mul_frac([1, 2], [1, 3]), [1, 6])
    def test_div_frac(self):
        self.assertEqual(div_frac([1, 2], [1, 3]), [3, 2])
    def test_is_positive(self):
        self.assertEqual(is_positive([2, 5]), True)
        self.assertEqual(is_positive([2, -1]), False)
        self.assertEqual(is_positive([-3, 1]), False)
        self.assertEqual(is_positive([-2, -5]), True)
    def test_is_zero(self):
        self.assertEqual(is_zero([0, -1]), True)
        self.assertEqual(is_zero([1, -4]), False)
    def test_cmp_frac(self):
        self.assertEqual(cmp_frac([2, 9], [6, 9]), -1)
        self.assertEqual(cmp_frac([2, 9], [2, 9]), 0)
        self.assertEqual(cmp_frac([3, 9], [2, 9]), 1)
    def test_frac2float(self):
        self.assertEqual(frac2float([3, 8]), 0.375)
    def tearDown(self):
        self.zero[:] = []

if __name__ == '__main__':
    unittest.main()     # uruchamia wszystkie testy
