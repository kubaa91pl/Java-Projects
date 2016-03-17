from points import *


class Rectangle:
    """Klasa reprezentujaca prostokat na plaszczyznie."""

    def __init__(self, x1=0, y1=0, x2=0, y2=0):
        self.pt1 = Point(x1, y1)
        self.pt2 = Point(x2, y2)
    def __str__(self):
        r1 = (self.pt1.x, self.pt1.y)
        r2 = (self.pt2.x, self.pt2.y)
        return [r1, r2]
        # "[(x1, y1), (x2, y2)]"
    def __repr__(self):        # "Rectangle(x1, y1, x2, y2)"
        rect = "Rectangle(%d, %d, %d, %d)" % (self.pt1.x, self.pt1.y, self.pt2.x, self.pt2.y)
        return rect
    def __eq__(self, other):   # obsluga rect1 == rect2
        if(self.pt1.x == other.pt1.x and self.pt1.y == other.pt1.y and self.pt2.x == other.pt2.x and self.pt2.y == other.pt2.y):
            return 1
    def __ne__(self, other):        # obsluga rect1 != rect2
        return not self == other
    def center(self):          # zwraca srodek prostokata
        r1 = (self.pt1.x+self.pt2.x)/2
        r2 = (self.pt1.y+self.pt2.y)/2
        return Point(r1,r2)
    def area(self):            # pole powierzchni
        r1 = self.pt2.x - self.pt1.x
        r2 = self.pt2.y - self.pt1.y
        return r1*r2
    def move(self, x, y):      # przesuniecie o (x, y)
        self.pt1.x = self.pt1.x + x
        self.pt2.x = self.pt2.x + x
        self.pt1.y = self.pt1.y + y
        self.pt2.y = self.pt2.y + y
        return self

