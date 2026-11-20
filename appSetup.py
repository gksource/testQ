def sum(a, b):
    return (a + b)

a = int(input('Enter 1st number: '))
b = int(input('Enter 2nd number: '))

print(f'Sum of {a} and {b} is {sum(a, b)}')


def insecure_socket_bind_noncompliant():
    import socket
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # Noncompliant: Empty IP Address is passed when binding to a socket.
    s.bind(('', 0))
