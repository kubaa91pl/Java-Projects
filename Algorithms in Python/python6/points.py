import math

class Point:
    """Klasa reprezentujaca punkty na plaszczyznie."""

    def __init__(self, x=0, y=0):  # konstruktor
        self.x = x
        self.y = y
    def __str__(self):# zwraca string "(x, y)"
        S = "(%d, %d)" % (self.x, self.y)
        return S
    def __repr__(self):# zwraca string "Point(x, y)"
        S = "Point(%d, %d)" % (self.x, self.y)
        return S
    def __eq__(self, other):   # obsluga point1 == point2
        if(self.x == other.x and self.y == other.y):
            return 1
    def __ne__(self, other):        # obsluga point1 != point2
        return not self == other
    # Punkty jako wektory 2D.
    def __add__(self, other):  # v1 + v2
        return Point(self.x+other.x, self.y+other.y)
    def __sub__(self, other):  # v1 - v2
        return Point(self.x-other.x, self.y-other.y)
    def __mul__(self, other):  # v1 * v2, iloczyn skalarny
        return self.x * other.x + self.y * other.y
    def cross(self, other):         # v1 x v2, iloczyn wektorowy 2D
        return self.x * other.y - self.y * other.x
    def length(self):          # dlugosc wektora
        a = math.pow(self.x, 2)
        b = math.pow(self.y, 2)
        result = math.sqrt(a+b)
        return result
