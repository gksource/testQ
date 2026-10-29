def sum(a, b):
    return (a + b)

a = int(input('Enter 1st number: '))
b = int(input('Enter 2nd number: '))

print(f'Sum of {a} and {b} is {sum(a, b)}')


def insecure_socket_bind_noncompliant():
    import socket
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # Compliant: bind only to the loopback interface.
    s.bind(('127.0.0.1', 0))
