#                                               -*- Autoconf -*-
# Process this file with autoconf to produce a configure script.

AC_PREREQ([2.69])

export CFLAGS

AC_INIT([Falcon-autoconf-test2], [0.0.1], [abc@gmail.com])
AM_INIT_AUTOMAKE(Falcon-autoconf-test2, 0.0.1)
AC_OUTPUT(Makefile src/Makefile doc/Makefile scripts/Makefile)

AC_CONFIG_SRCDIR([src/helloWorld.c])
AC_CONFIG_HEADERS([config.h])

dnl # Enable printf color

AC_ARG_ENABLE(falconprint,
[  --enable-falconprint      enables Falcon print features.],
[ case "$enableval" in
  no)
    FalconPrint=no
    ;;
  *)
    FalconPrint=yes
  esac ]
)

if test "x$FalconPrint" = "xyes"; then
	AC_MSG_NOTICE([enables Falcon print features])
  	: ${CFLAGS=-DFALCONPRINT}
fi

# Checks for programs.
AC_PROG_CC
AC_PROG_INSTALL

# Checks for libraries.

# Checks for header files.
AC_CHECK_HEADERS([unistd.h])

# Checks for typedefs, structures, and compiler characteristics.

# Checks for library functions.
AC_CHECK_FUNCS([getlogin_r])


AC_OUTPUT
