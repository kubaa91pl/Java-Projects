def function_p(i, j):
    P = {}
    P[0, 0] = 0.5
    P[1, 0] = 0.0
    P[0, 1] = 1.0

    if (i, j) not in P:
        if i == 0:
            return 1.0
        elif j == 0:
            return 0.0
        P[i, j] = 0.5 * (function_p(i-1, j) + function_p(i, j-1))
    return P[i, j]

function_p(10,10)

def recursive_p(i, j):
    if i == 0:
        return 1.0
    elif j == 0:
        return 0.0
    else:
        return 0.5 * (recursive_p(i-1, j) + recursive_p(i, j-1))

print recursive_p(1,5)
print function_p(1,5)