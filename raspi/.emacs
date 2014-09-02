(setq inhibit-startup-screen 1)
(setq initial-scratch-message "")
(tool-bar-mode 0)
(tooltip-mode 0)
(menu-bar-mode 0)
(size-indication-mode 1)
(column-number-mode 1)

(prefer-coding-system 'utf-8)
(show-paren-mode)

(desktop-save-mode 1)


; Marmelade

(require 'package)

(add-to-list 
 'package-archives
 '("marmalade" . "http://marmalade-repo.org/packages/"))
(package-initialize)

;; SLIME for CLISP
;;
;; Setup load-path, autoloads and your lisp system
(add-to-list 'load-path "~/others/slime")
(require 'slime-autoloads)
(setq inferior-lisp-program "/usr/bin/clisp")
