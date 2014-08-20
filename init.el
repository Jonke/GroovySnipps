(when (>= emacs-major-version 24)
  (require 'package)
  (package-initialize)
  (add-to-list 'package-archives '("melpa" . "http://melpa.milkbox.net/packages/") t)
)

(require 'multiple-cursors)
(global-set-key (kbd "C-c C-<") 'mc/mark-all-like-this)


(setenv "PATH" (concat (getenv "PATH") ":/home/jonke/.cabal/bin"))
(setq exec-path (append exec-path '("/home/jonke/.cabal/bin")))
(add-hook 'after-init-hook #'global-flycheck-mode)
